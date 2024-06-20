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
    sshUserPrivateKey= credentials('mk-server')
    }
    stages {
        stage("Build") {
            steps {
                script{
                    sh 'mvn clean install -DskipTests'
                }
            }
        }/* 
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
        }
        stage("docker-publish") {
            steps {
                script{
                    sh """
                    docker build -t mayureshkosandar/webgoat:1.0 .
                    docker login -u ${DOCKERHUB_CREDENTIALS_USR} -p ${DOCKERHUB_CREDENTIALS_PSW}
                    docker push mayureshkosandar/webgoat:1.0
                    """
                }
            }
        }       
        stage('prod-deployment') {
            agent {
                docker {
                    image 'alpine:latest'
                    // Run the container on the node specified at the
                    // top-level of the Pipeline, in the same workspace,
                    // rather than on a new node entirely:
                    //reuseNode true
                }
            }
            steps {
                sh """
                apk update
                apk add --no-cache openssh-client
                apk add sshpass
                sshpass -p mk ssh -tt mk@192.168.92.114
                "docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0"
                """
            }
        }*/
        stage("prod-deployment") {
            steps {
                script{
                    docker.image('mayureshkosandar/alpine:1.0').inside('-u root') {
                        sh"""
                        apk update
                        apk add --no-cache openssh-client
                        ssh -i ${sshUserPrivateKey} mk@192.168.92.114
                        docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0
                        """
                    }
                    //sh """
                    //docker rm -f webgoat
                    //docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0 
                    //"""
                }
            }
        }
    }
}
