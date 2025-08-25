pipeline {
    agent any

    parameters {
        choice(name: 'DEPLOY_ENV', choices: ['test', 'prod'], description: 'Selecciona el entorno de despliegue')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ebueno81/api-fastdye-apps.git'
            }
        }

        stage('Configurar variables de entorno') {
            steps {
                script {
                    if (params.DEPLOY_ENV == 'test') {
                        withCredentials([
                            string(credentialsId: 'DB_URL_TEST', variable: 'DB_URL'),
                            string(credentialsId: 'DB_USER_TEST', variable: 'DB_USER'),
                            string(credentialsId: 'DB_PASS_TEST', variable: 'DB_PASS'),
                            string(credentialsId: 'PROFILE_ACTIVE_TEST', variable: 'PROFILE_ACTIVE')
                        ]) {
                            env.DB_URL = DB_URL
                            env.DB_USER = DB_USER
                            env.DB_PASS = DB_PASS
                            env.PROFILE_ACTIVE = PROFILE_ACTIVE
                        }
                    } else {
                        withCredentials([
                            string(credentialsId: 'DB_URL_PROD', variable: 'DB_URL'),
                            string(credentialsId: 'DB_USER_PROD', variable: 'DB_USER'),
                            string(credentialsId: 'DB_PASS_PROD', variable: 'DB_PASS'),
                            string(credentialsId: 'PROFILE_ACTIVE_PROD', variable: 'PROFILE_ACTIVE')
                        ]) {
                            env.DB_URL = DB_URL
                            env.DB_USER = DB_USER
                            env.DB_PASS = DB_PASS
                            env.PROFILE_ACTIVE = PROFILE_ACTIVE
                        }
                    }
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                  docker build \
                    --build-arg DB_URL=${env.DB_URL} \
                    --build-arg DB_USER=${env.DB_USER} \
                    --build-arg DB_PASS=${env.DB_PASS} \
                    --build-arg PROFILE_ACTIVE=${env.PROFILE_ACTIVE} \
                    -t ebueno81/apiapps:${params.DEPLOY_ENV} .
                """
            }
        }

        stage('Deploy to Server') {
            steps {
                sshagent(['vm-ssh']) {
                    sh """
                      ssh -o StrictHostKeyChecking=no root@45.149.207.118 "
                        docker rm -f ctnapiapps || true &&
                        docker run -d --name ctnapiapps --restart unless-stopped -p 5015:8080 \
                          -e SPRING_DATASOURCE_URL=${env.DB_URL} \
                          -e SPRING_DATASOURCE_USERNAME=${env.DB_USER} \
                          -e SPRING_DATASOURCE_PASSWORD=${env.DB_PASS} \
                          -e SPRING_PROFILES_ACTIVE=${env.PROFILE_ACTIVE} \
                          ebueno81/apiapps:${params.DEPLOY_ENV}
                      "
                    """
                }
            }
        }
    }
}
