pipeline {
    agent any
    options {
            skipStagesAfterUnstable()
    }
    environment {
        SERVICE_NAME = "crbk-project"
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew build -x test'
            }
        }
        stage('Test') {
            steps {
                sh './gradlew test'
            }
        }
        stage('Build Docker image') {
            steps {
                sh 'docker build -t ${SERVICE_NAME}:latest -t ${SERVICE_NAME}:${BUILD_NUMBER} .'
            }
        }
        stage('Deploy') {
           steps {
                sh 'docker service update crbk-iot_${SERVICE_NAME}'
           }
        }
    }
}
