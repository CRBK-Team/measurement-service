pipeline {
    agent any
    options {
            skipStagesAfterUnstable()
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
                sh './gradlew bootBuildImage'
            }
        }
        stage('Deploy') {
           steps {
                sh 'docker service update crbk-iot_crbk-project'
           }
        }
    }
}
