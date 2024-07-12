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
    PRIVATE_KEY= credentials('mk_server')
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
        }*/
        stage("deploy-to-devsecops") {
            steps {
                script{
                    sh """
                    docker login -u ${DOCKERHUB_CREDENTIALS_USR} -p ${DOCKERHUB_CREDENTIALS_PSW}
                    sshPublisher(publishers: [sshPublisherDesc(configName: 'devsecops', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                    """
                }
            }
        }
        /*
        stage('prod-deployment'){
            agent {
                docker {
                    image 'alpine:latest'
                    args ' -u root '
                }
            }
            environment {
                SSH_CREDENTIALS_ID = 'mk_server' 
                HOST_IP = '192.168.92.114' 
            }
            steps{
                sh """
                apk update
                apk add --no-cache openssh-client
                ssh-keygen -t rsa -b 4096 -f ~/.ssh/id_rsa -P ""
                cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
                """
                sshagent([env.SSH_CREDENTIALS_ID]) {
                    sh"""
                    unset SSH_AUTH_SOCK;
                    unset SSH_AGENT_PID;
                    ssh -o StrictHostKeyChecking=no mk@${env.HOST_IP} "docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0"
                    """
                }
            } 
        }
        stage('prod-deployment') {
            agent {
                docker {
                    image 'alpine:latest'
                    args ' -u root '
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
                sshagent(['mk_server']) {
                    sh 'ssh mk@192.168.92.114 "docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0"'
                }
                echo $PRIVATE_KEY >> /tmp/id_rsa
                chmod 600 /tmp/id_rsa
                ssh -i /tmp/id_rsa -tt mk@192.168.92.114
                docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0
                """
                }
        }
        stage("prod-deployment") {
            steps {
                script{
                    docker.image('alpine:latest').inside('-u root -v $SSH_AUTH_SOCK:/ssh-agent -e SSH_AUTH_SOCK=/ssh-agent') {
                        sh """
                        apk update
                        apk add --no-cache openssh-client
                        ssh -T mk@192.168.92.114
                        docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0
                        """
                    }
                    //sh """
                    //docker rm -f webgoat
                    //docker run -dit -p 9090:8080 --name webgoat mayureshkosandar/webgoat:1.0 
                    //"""
                }
            }
        }*/
    }
}
