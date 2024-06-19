pipeline {
    agent any

    tools {
        maven 'Maven 3.6.3' // Sesuaikan dengan versi Maven yang terinstal
        jdk 'JDK 11'        // Sesuaikan dengan versi JDK yang terinstal
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/SetiaBudii/webautomationtesting.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Generate Report') {
            steps {
                cucumber 'target/cucumber-report.json'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true
            junit 'target/surefire-reports/*.xml'
        }
    }
}
