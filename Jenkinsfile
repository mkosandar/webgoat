pipeline {
    agent any
    tools {
        //gradle "7.4.2"
        //jdk "JDK15"
        maven "mvn 3.9.6"
        dockerTool "docker"
    }
    stages {
        stage("Build") {
            steps {
                script{
                    sh 'mvn clean install -DskipTests'
                }
            }
        }  
        stage("Unit-test") {
            steps {
                script{
                     echo "unit test case"
                }
            }
        }
        stage("snyk") {
            steps {
                script{
                    snykSecurity failOnIssues: false, snykInstallation: 'snyk', snykTokenId: 'snyk-token'
                }
            }
        } 
        stage('Secret Detection') {
            steps {
                script {
                    docker.image('trufflesecurity/trufflehog').inside {
                        sh 'trufflehog git https://github.com/mkosandar/webgoat.git'
                    }
                }
            }
        }
    }
}
