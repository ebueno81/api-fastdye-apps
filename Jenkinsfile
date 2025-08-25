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
                        docker pull ebueno81/apiapps:latest &&
                        docker rm -f ctnapiapps || true &&
                        docker run -d --name ctnapiapps --restart unless-stopped -p 5015:8080 ebueno81/apiapps:latest
                      "
                    '''
                }
            }
        }
    }
}
