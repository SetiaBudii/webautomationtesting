pipeline {
    agent {
        docker {
            image 'maven:3.9.8-jdk-11'
            args '-v /dev/shm:/dev/shm' // Important for running browsers in Docker
        }
    }

    tools {
        maven 'Maven 3.9.8'
        jdk 'JDK 11'
    }

    environment {
        JAVA_HOME = tool name: 'JDK 11', type: 'jdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo/your-project.git'
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
