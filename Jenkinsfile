// // pipeline {
// //     agent any

// //     tools {
// //         maven 'Maven 3.9.8' 
// //         jdk 'JDK 11'       
// //     }

// //     environment {
// //         JAVA_HOME = tool name: 'JDK 11', type: 'jdk'
// //         PATH = "${JAVA_HOME}/bin:${env.PATH}"
// //     }

// //     stages {
// //         stage('Delete exist screenshots') {
// //             steps {
// //                 script {
// //                     def screenshotDir = "${env.WORKSPACE}/screenshots"
// //                     echo "Checking folder: ${screenshotDir}"
// //                     if (fileExists(screenshotDir)) {
// //                         echo "Deleting folder: ${screenshotDir}"
// //                         sh "rm -rf ${screenshotDir}"
// //                     }
// //                 }
// //             }
// //         }

// //         stage('Checkout') {
// //             steps {
// //                 git 'https://github.com/SetiaBudii/webautomationtesting.git'
// //             }
// //         }

// //         stage('Test') {
// //             steps {
// //                 sh 'mvn test'
// //             }
// //         }

// //         stage('Generate Report') {
// //             steps {
// //                 cucumber 'result/cucumber-report.json'
// //             }
// //         }
// //     }

// //     post {
// //         always {
// //             archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
// //             // junit 'target/surefire-reports/*.xml'
// //             // publishCucumberReports(
// //             //     jsonReportDirectory: 'target',
// //             //     reportTitle: 'Cucumber Report'
// //             // )
// //         }
// //     }
// // }
// pipeline {
//     agent any

//     tools {
//         maven 'Maven 3.9.8' 
//         jdk 'JDK 11'       
//     }

//     environment {
//         JAVA_HOME = tool name: 'JDK 11', type: 'jdk'
//         PATH = "${JAVA_HOME}/bin:${env.PATH}"
//     }

//     stages {
//         stage('Delete exist screenshots') {
//             steps {
//                 script {
//                     def screenshotDir = "${env.WORKSPACE}/screenshots"
//                     echo "Checking folder: ${screenshotDir}"
//                     if (fileExists(screenshotDir)) {
//                         echo "Deleting folder: ${screenshotDir}"
//                         sh "rm -rf ${screenshotDir}"
//                     }
//                 }
//             }
//         }

//         stage('Checkout') {
//             steps {
//                 git 'https://github.com/SetiaBudii/webautomationtesting.git'
//             }
//         }

//         stage('Test') {
//             steps {
//                 sh 'mvn test'
//             }
//         }
//     }

//  post {
//         always {
//             script {
//               sh 'python3 parsing_result.py'
//             }
//             archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
//             archiveArtifacts artifacts: 'results_summary.txt', allowEmptyArchive: true
//         }
//     }
// }

pipeline {
    agent any

    tools {
        maven 'Maven 3.9.8' 
        jdk 'JDK 11'       
    }

    environment {
        JAVA_HOME = tool name: 'JDK 11', type: 'jdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
        CLICKUP_API_TOKEN = 'pk_96750405_83LRC7IDFG5D865SR6OFHCYI9V3EOLUO'
        TASK_ID = '86epr4har'
    }

    stages {
        stage('Delete exist screenshots') {
            steps {
                script {
                    def screenshotDir = "${env.WORKSPACE}/screenshots"
                    echo "Checking folder: ${screenshotDir}"
                    if (fileExists(screenshotDir)) {
                        echo "Deleting folder: ${screenshotDir}"
                        sh "rm -rf ${screenshotDir}"
                    }
                }
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/SetiaBudii/webautomationtesting.git'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            script {
                // Parse result using Python script
                sh 'python3 parsing_result.py'

                // Archive artifacts
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
                archiveArtifacts artifacts: 'results_summary.txt', allowEmptyArchive: true

                // Send screenshots as attachments to ClickUp
                def screenshotFiles = sh(
                    script: 'find screenshots -name "*.png"',
                    returnStdout: true
                ).trim().split('\n')

                for (file in screenshotFiles) {
                    if (file.trim()) {
                        def response = httpRequest(
                            httpMode: 'POST',
                            url: "https://api.clickup.com/api/v2/task/${env.TASK_ID}/attachment",
                            customHeaders: [
                                [name: 'Authorization', value: "Bearer ${env.CLICKUP_API_TOKEN}"],
                                [name: 'Content-Type', value: 'multipart/form-data']
                            ],
                            uploadFile: file.trim(),
                            multipartName: 'attachment',
                            acceptType: 'APPLICATION_JSON'
                        )
                        echo "Uploaded ${file} to ClickUp. Response: ${response.content}"
                    }
                }
            }
        }
    }
}
