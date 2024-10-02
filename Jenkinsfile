
pipeline {
      agent any
      stages {
        stage('maven Build'){
          steps{
            sh '''  mvn clean
	    	    mvn test
	  	    mvn package
    		'''
          }
        }
         stage ('Docker Image Build') {
          steps {
             sh '''cd $WORKSPACE
                   docker build -t pet-clinic:v${BUILD_NUMBER} .'''
             }
           }
           stage('Push Image to ECR'){
            steps {
                sh '''aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 559050209565.dkr.ecr.us-east-1.amazonaws.com
           		    
			                 docker tag pet-clinic:v${BUILD_NUMBER} 559050209565.dkr.ecr.us-east-1.amazonaws.com/pet-clinic:v${BUILD_NUMBER}
                    	 docker 559050209565.dkr.ecr.us-east-1.amazonaws.com/pet-clinic:v${BUILD_NUMBER}
		   
       '''
            }
          }
      //  stage('deploy into k8'){
       //   steps{
       //        sh''' kubectl apply petclinic.yaml
       //              kubectl apply petsvc.yaml
       //           '''
       //   }      
       // }
        }
}
