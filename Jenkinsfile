pipeline {
    agent any
    stages {
        stage('Clean Workspace') {
            steps {
                sh 'rm -rf *'
            }
        }
//         stage('Clone the repository') {
//             steps {
//                 git url: 'http://gitea:3000/admin/project.git'
//                 sh 'tree .'
//             }
//         }
        stage('Build') {
            steps {
                dir('') {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }
        stage('Run a Test') {
            steps {
                dir('') {
                    sh 'mvn -Dtest=AddAssignmentTest verify'
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            }
        }
    }
}
