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

//         stage('Generate Report') {
//             steps {
//                 cucumber 'result/cucumber-report.json'
//             }
//         }
//     }

//     post {
//         always {
//             archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
//             // junit 'target/surefire-reports/*.xml'
//             // publishCucumberReports(
//             //     jsonReportDirectory: 'target',
//             //     reportTitle: 'Cucumber Report'
//             // )
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
                def jsonFile = "result/cucumber-report.json"
                def resultsSummaryFile = 'results_summary.txt'

                def jsonContent = readFile(jsonFile)
                def jsonSlurper = new groovy.json.JsonSlurper()
                def testResults = jsonSlurper.parseText(jsonContent)

                def resultsSummary = new StringBuilder()
                testResults.each { feature ->
                    resultsSummary.append("Feature: ${feature.name}\n")
                    feature.elements.each { scenario ->
                        resultsSummary.append("  Scenario: ${scenario.name}\n")
                        scenario.steps.each { step ->
                            def stepStatus = step.result.status
                            def stepName = step.name
                            resultsSummary.append("    ${step.keyword} ${stepName} - ${stepStatus}\n")
                            if (stepStatus == "failed") {
                                def errorMessage = step.result.error_message ?: "No error message"
                                resultsSummary.append("      Error: ${errorMessage}\n")
                            }
                        }
                    }
                    resultsSummary.append("\n")
                }

                // Menulis ringkasan hasil tes ke file
                writeFile file: resultsSummaryFile, text: resultsSummary.toString()

                // Menampilkan ringkasan hasil tes di console Jenkins
                echo "Test Results Summary:"
                echo resultsSummary.toString()

                // Mengarsipkan artefak
                archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
                archiveArtifacts artifacts: resultsSummaryFile, allowEmptyArchive: true
            }
        }
    }
}
