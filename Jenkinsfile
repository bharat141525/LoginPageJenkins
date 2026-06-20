pipeline {

    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Clone Repository') {

            steps {
                git branch: 'main',
                url: 'https://github.com/bharat141525/LoginPageJenkins.git'
            }
        }

        stage('Build') {

            steps {
                bat 'mvn clean compile'
            }
        }

        stage('Run Tests') {

            steps {
                bat 'mvn test'
            }
        }
    }

    post {

        always {

            publishTestNGResults(
                    testResultsPattern: 'test-output/testng-results.xml'
            )
        }

        success {

            echo 'Pipeline executed successfully.'
        }

        failure {

            echo 'Pipeline execution failed.'
        }
    }
}
