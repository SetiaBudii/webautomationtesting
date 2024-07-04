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
                def scriptContent = '''
                #!/bin/bash
                set -e  # Exit immediately if a command exits with a non-zero status.
                
                echo "Checking if jq is installed..."
                if ! command -v jq &> /dev/null; then
                    echo "jq could not be found. Please install jq to run this script."
                    exit 1
                fi

                echo "Checking if JSON file exists..."
                if [ ! -f ${jsonFile} ]; then
                    echo "JSON file not found!"
                    exit 1
                fi

                echo "Contents of JSON file:"
                cat ${jsonFile}

                echo "Parsing JSON file..."
                # Parse the JSON file and extract the necessary information
                jq -r '
                    .[] | .elements[] | select(.type == "scenario") |
                    "\\(.name) - \\(.steps[] | select(.result.status == \\"failed\\") | \\"FAILED\\\\nActual: \\(.result.error_message | split(\\\\\\"\\\\n\\\\")[0])\\\\nExpected: \\(.match.arguments[0].val)\\")",
                    "\\(.name) - \\(.steps[] | select(.result.status == \\"passed\\") | \\"PASSED\\")"
                ' ${jsonFile}
                '''.stripIndent()

                echo "Writing parse_results.sh script..."
                writeFile file: 'parse_results.sh', text: scriptContent
                sh 'chmod +x parse_results.sh'

                echo "Executing parse_results.sh..."
                sh './parse_results.sh > results_summary.txt'

                echo "Test Results Summary:"
                sh 'cat results_summary.txt'
            }
            archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            archiveArtifacts artifacts: 'results_summary.txt', allowEmptyArchive: true
        }
    }
}
