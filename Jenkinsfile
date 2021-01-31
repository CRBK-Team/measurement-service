pipeline {
    agent any
    options {
            skipStagesAfterUnstable()
    }
    environment {
        ENV_NAME = "dev"
        PROJECT_STACK_NAME = "crbk-project"
        SERVICE_NAME = "measurement-service"
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
                sh 'docker build -t ${PROJECT_STACK_NAME}-${SERVICE_NAME}:latest -t ${PROJECT_STACK_NAME}-${SERVICE_NAME}:${BUILD_NUMBER} .'
            }
        }
        stage('Deploy') {
           steps {
                sh 'docker service update ${ENV_NAME}-${PROJECT_STACK_NAME}_${SERVICE_NAME}'
           }
        }
    }
}
