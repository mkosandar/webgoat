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
                    sh """
                    docker run -dit ghcr.io/trufflesecurity/trufflehog:latest git https://github.com/mkosandar/webgoat.git > output.txt
                    cat output.txt |grep -oE "\"stringsFound\"\:.[.\"]}"|sed -e "s/,\".]//" -e "s/}//"|sed "s/\"stringsFound\"://"|grep -o "\".\""|awk -F "," '{ for(i=1;i<=NF;i++) print $i}'
                    """
                    //docker.image('dxa4481/trufflehog').inside {
                    //    sh 'trufflehog --regex https://github.com/mkosandar/webgoat.git'
                    //docker.image('ghcr.io/trufflesecurity/trufflehog:latest').inside {
                        //    sh 'trufflehog git https://github.com/mkosandar/webgoat.git'
                    }
                }
            }
        }
    }

