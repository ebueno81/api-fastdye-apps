pipeline {
    agent any

    environment {
        IMAGE_NAME = "ebueno81/apiapps"
        TAG = "latest"
        CONTAINER_NAME = "ctnapiapps"
        SERVER = "45.149.207.118"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Configurar entorno') {
            steps {
                script {
                    def branch = env.BRANCH_NAME ?: env.GIT_BRANCH
                    echo "Branch detectado: ${branch}"

                    if (branch == "main" || branch == "origin/main") {
                        env.DEPLOY_ENV = "prod"
                        env.DB_URL_CRED = "DB_URL_PROD"
                        env.DB_USER_CRED = "DB_USER_PROD"
                        env.DB_PASS_CRED = "DB_PASS_PROD"
                        env.PROFILE_ACTIVE = "pdn"
                    } else if (branch == "qa" || branch == "origin/qa") {
                        env.DEPLOY_ENV = "test"
                        env.DB_URL_CRED = "DB_URL_QA"
                        env.DB_USER_CRED = "DB_USER_QA"
                        env.DB_PASS_CRED = "DB_PASS_QA"
                        env.PROFILE_ACTIVE = "qa"
                    } else {
                        error("La rama ${branch} no tiene despliegue configurado")
                    }

                    echo "Entorno de despliegue: ${env.DEPLOY_ENV}"
                }
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME}:${TAG} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh """
                      echo "$PASS" | docker login -u "$USER" --password-stdin
                      docker push ${IMAGE_NAME}:${TAG}
                    """
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent(['vm-ssh']) {
                    sh '''
                      ssh -o StrictHostKeyChecking=no root@45.149.207.118 "
                        docker pull ebueno81/apiapps:latest &&
                        docker rm -f ctnapiapps || true &&
                        docker run -d --name ctnapiapps --restart unless-stopped -p 5015:8080 \
                          -e SPRING_PROFILES_ACTIVE=${PROFILE_ACTIVE} \
                          -e SPRING_DATASOURCE_URL=${DB_URL} \
                          -e SPRING_DATASOURCE_USERNAME=${DB_USER} \
                          -e SPRING_DATASOURCE_PASSWORD=${DB_PASS} \
                          ebueno81/apiapps:latest
                      "
                    '''
                }
            }
        }
    }
}
