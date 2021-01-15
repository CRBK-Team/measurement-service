pipeline {
    agent any
    options {
            skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build -x test'
            }
        }
        stage('Test') {
            steps {
                sh 'gradle test'
                junit 'reports/**/*.xml'
            }
        }
        stage('Build Docker image') {
            steps {
                sh 'gradle bootBuildImage'
            }
        }
        stage('Deploy') {
            steps {
                // for implementation
            }
        }
    }
}
