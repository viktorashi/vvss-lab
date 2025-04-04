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
                sh 'git clone http://gitea:3000/admin/project.git'
                sh 'tree .'
            }
        }
        stage('Build') {
            steps {
                dir('project') {
                    sh 'mvn clean install -DskipTests'
                }
            }
        }
        stage('Run a Test') {
            steps {
                dir('project') {
                    sh 'mvn -Dtest=AddAssignmentTest verify'
                }
            }
        }
        stage('Publish Allure Report') {
            steps {
                allure includeProperties: false, jdk: '', results: [[path: 'project/target/allure-results']]
            }
        }
    }
}
