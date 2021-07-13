job('NodeJS example') { // Job NAME
    scm { // Configure Source control management 
        git('https://github.com/tamerIbraheem/docker-cicd') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@domain.com')
        }
    }
    triggers { // Configure when to check for changes 
        scm('H/5 * * * *')
    }
    wrappers {
         // this is the name of the NodeJS installation in nodejs('nodejs')
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps { // what steps to take 
        dockerBuildAndPublish {
            repositoryName('https://github.com/tamerIbraheem/docker-cicd')
            tag('${GIT_REVISION,length=9}')
            buildContext('./basics')
            registryCredentials('tamer-dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }

    }
}
