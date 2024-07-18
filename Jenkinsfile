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
        jdk 'JDK 22'       
    }

    environment {
        JAVA_HOME = tool name: 'JDK 22', type: 'jdk'
        PATH = "${JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        // stage('Delete exist screenshots') {
        //     steps {
        //         script {
        //             def screenshotDir = "${env.WORKSPACE}/screenshots"
        //             echo "Checking folder: ${screenshotDir}"
        //             if (fileExists(screenshotDir)) {
        //                 echo "Deleting folder: ${screenshotDir}"
        //                 sh "rm -rf ${screenshotDir}"
        //             }
        //         }
        //     }
        // }

        stage('Clean Workspace') {
            steps {
                // Clean before build
                cleanWs()
                // We need to explicitly checkout from SCM here
                    checkout scm
                    echo "Building ${env.JOB_NAME}..."
            }
        }
        stage('Checkout') {
            steps {
                git 'https://github.com/SetiaBudii/webautomationtesting.git'
            }
        }

        // stage('Update Config') {
        //     steps {
        //         script {
        //             // Path ke file konfigurasi
        //             def configFilePath = 'src/test/resources/config.properties'
                    
        //             // Menggunakan perintah sed untuk mengganti nilai variabel dalam file
        //             sh "sed -i 's/^driver=\"local\"/driver=\"remote\"/' ${configFilePath}"
                    
        //             // Menampilkan pesan bahwa variabel telah berhasil diperbarui
        //             echo "Variable 'driver' in ${configFilePath} has been updated to 'remote'"
        //         }
        //     }
        // }

        stage('Test') {
            steps {
                // sh 'mvn test'
                sh 'java -version'
            }
        }
    }

 post {
        always {
            // script {
            //   sh 'python3 parsing_result.py'
            //   sh 'python3 postattachement.py'
            // }
            archiveArtifacts artifacts: 'screenshots/*.png', allowEmptyArchive: true
            archiveArtifacts artifacts: 'results_summary.txt', allowEmptyArchive: true
        }
    }
}
