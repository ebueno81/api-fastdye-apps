pipeline {
    agent any

    environment {
        IMAGE_NAME = "ebueno81/apiapps"
        TAG = "latest"
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
                        env.IMAGE_NAME = "ebueno81/apiapps"
                        env.CONTAINER_NAME = "ctnapiapps"
                        env.PROFILE_ACTIVE = "pdn"
                        env.DB_URL_CRED = "DB_URL_PROD"
                        env.DB_USER_CRED = "DB_USER_PROD"
                        env.DB_PASS_CRED = "DB_PASS_PROD"
                    } else if (branch == "qa" || branch == "origin/qa") {
                        env.IMAGE_NAME = "ebueno81/apiapps-qa"
                        env.CONTAINER_NAME = "ctnapiapps-qa"
                        env.PROFILE_ACTIVE = "qa"
                        env.DB_URL_CRED = "DB_URL_QA"
                        env.DB_USER_CRED = "DB_USER_QA"
                        env.DB_PASS_CRED = "DB_PASS_QA"
                    } else {
                        error("La rama ${branch} no tiene despliegue configurado")
                    }
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
                sh "docker build -t ${env.IMAGE_NAME}:${env.TAG} ."
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                    sh """
                        echo "$PASS" | docker login -u "$USER" --password-stdin
                        docker push ${env.IMAGE_NAME}:${env.TAG}
                    """
                }
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent(['vm-ssh']) {
                    withCredentials([
                        string(credentialsId: "${env.DB_URL_CRED}", variable: 'DB_URL'),
                        string(credentialsId: "${env.DB_USER_CRED}", variable: 'DB_USER'),
                        string(credentialsId: "${env.DB_PASS_CRED}", variable: 'DB_PASS')
                    ]) {
                        sh """
                          ssh -o StrictHostKeyChecking=no root@${env.SERVER} '
                            docker pull ${env.IMAGE_NAME}:${env.TAG} &&
                            docker rm -f ${env.CONTAINER_NAME} || true &&
                            docker run -d --name ${env.CONTAINER_NAME} --restart unless-stopped -p 5015:8080 \
                              -e SPRING_PROFILES_ACTIVE=${env.PROFILE_ACTIVE} \
                              -e SPRING_DATASOURCE_URL="${DB_URL}" \
                              -e SPRING_DATASOURCE_USERNAME="${DB_USER}" \
                              -e SPRING_DATASOURCE_PASSWORD="${DB_PASS}" \
                              ${env.IMAGE_NAME}:${env.TAG}
                          '
                        """
                    }
                }
            }
        }
    }
}
