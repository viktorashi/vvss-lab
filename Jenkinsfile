pipeline {
    agent any
    stages {
        stage('Clean Workspace') {
            steps {
                sh 'rm -rf *'
            }
        }
        stage('Clone the repository') {
            steps {
                git branch: 'main', url: 'http://gitea:3000/admin/project.git'
                sh 'tree .'
            }
        }
        stage('Build and test') {
            steps {
                    sh 'mvn clean install -Dtest=AddAssignmentTest,AddStudentsTest verify'
            }
        }
        
        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '',  results: [[path: 'target/allure-results']]
            }
        }
    }
}
