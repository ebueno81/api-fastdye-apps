pipeline {
    agent any

    environment {
        IMAGE_NAME = "ebueno81/miapp"
        TAG = "latest"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ebueno81/api-fastdye-apps.git'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh """
                  docker build -t ${IMAGE_NAME}:${TAG} .
                """
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
                        docker pull ebueno81/miapp:latest &&
                        docker rm -f miapp || true &&
                        docker run -d --name miapp --restart unless-stopped -p 8081:8080 ebueno81/miapp:latest
                      "
                    '''
                }
            }
        }
    }
}
