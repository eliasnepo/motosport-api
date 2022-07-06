terraform {
    required_version = ">=0.13.1"
    required_providers {
      aws = ">=3.54.0"
    }
    # backend "s3" {
    #   bucket = "myfcbucket"
    #   key    = "terraform.tfstate"
    #   region = "us-east-1"
    # }
}

provider "aws" {
  region = "us-east-1"
  access_key = "test123"
  secret_key = "testabc"
  skip_credentials_validation = true
  skip_requesting_account_id = true
  skip_metadata_api_check = true
  s3_force_path_style = true
  endpoints {
    s3 = "http://localhost:4566"
  }
}