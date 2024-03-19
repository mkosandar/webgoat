pipeline {
    agent any
    tools {
        //gradle "7.4.2"
        //jdk "JDK15"
        maven "mvn 3.9.6"
    }
    stages {
        stage("Build") {
            steps {
                script{
                    echo 'mvn clean install -DskipTests'
                }
            }
        }  
        stage("Unit-test") {
            steps {
                script{
                     sh "unit test case"
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
    }
}
