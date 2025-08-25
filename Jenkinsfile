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
                // Esto ya no usa '*/main', sino que Jenkins detecta la rama en ejecución
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
                        env.PROFILE_ACTIVE_CRED = "PROFILE_ACTIVE_PROD"
                    } else if (branch == "qa" || branch == "origin/qa") {
                        env.DEPLOY_ENV = "test"
                        env.DB_URL_CRED = "DB_URL_TEST"
                        env.DB_USER_CRED = "DB_USER_TEST"
                        env.DB_PASS_CRED = "DB_PASS_TEST"
                        env.PROFILE_ACTIVE_CRED = "PROFILE_ACTIVE_TEST"
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
                    withCredentials([
                        string(credentialsId: "${DB_URL_CRED}", variable: 'DB_URL'),
                        string(credentialsId: "${DB_USER_CRED}", variable: 'DB_USER'),
                        string(credentialsId: "${DB_PASS_CRED}", variable: 'DB_PASS'),
                        string(credentialsId: "${PROFILE_ACTIVE_CRED}", variable: 'PROFILE_ACTIVE')
                    ]) {
                        sh """
                          ssh -o StrictHostKeyChecking=no root@${SERVER} '
                            docker pull ${IMAGE_NAME}:${TAG} &&
                            docker rm -f ${CONTAINER_NAME} || true &&
                            docker run -d --name ${CONTAINER_NAME} --restart unless-stopped -p 5015:8080 \
                              -e SPRING_PROFILES_ACTIVE=${PROFILE_ACTIVE} \
                              -e SPRING_DATASOURCE_URL=${DB_URL} \
                              -e SPRING_DATASOURCE_USERNAME=${DB_USER} \
                              -e SPRING_DATASOURCE_PASSWORD=${DB_PASS} \
                              ${IMAGE_NAME}:${TAG}
                          '
                        """
                    }
                }
            }
        }
    }
}
