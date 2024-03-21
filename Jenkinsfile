pipeline {
    agent any
    tools {
        //gradle "7.4.2"
        //jdk "JDK15"
        maven "mvn 3.9.6"
        dockerTool "docker"
    }
    environment {     
    DOCKERHUB_CREDENTIALS= credentials('dockerHub-login')
    }
    stages {
        /*stage("Build") {
            steps {
                script{
                    sh 'mvn clean install -DskipTests'
                }
            }
        }  
        stage("Unit-test") {
            steps {
                script{
                     echo "mvn test" 
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
                    sh " docker run --rm hysnsec/trufflehog git https://github.com/mkosandar/webgoat.git --json |tee trufflehog-output.json"
                    //docker.image('dxa4481/trufflehog').inside {
                    //    sh 'trufflehog --regex https://github.com/mkosandar/webgoat.git'
                    //docker.image('ghcr.io/trufflesecurity/trufflehog:latest').inside('--entrypoint=""') {
                        //sh "trufflehog git --help"
                        //sh "trufflehog github --help"
                        //sh "trufflehog --entropy=NO --regex https://github.com/mkosandar/webgoat"
                        //sh "trufflehog https://github.com/mkosandar/webgoat"
                    //}
                }
            }
        }*/
        stage("prod-deployment") {
            steps {
                script{
                    sh """
                    docker rm -f webgoat
                    docker build -t mayureshkosandar/webgoat:1.o .
                    docker login -u ${DOCKERHUB_CREDENTIALS_USR} -p ${DOCKERHUB_CREDENTIALS_PSW}
                    docker push mayureshkosandar/webgoat:1.0
                    docker run -dit -p 9090:8080 --name webgoat webgoat:1.0 
                    //sshPublisher(publishers: [sshPublisherDesc(configName: '', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'startup.sh', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: 'opt/tomcat/', remoteDirectorySDF: false, removePrefix: '', sourceFiles: 'file-name')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                    """
                }
            }
        }
    }
}
