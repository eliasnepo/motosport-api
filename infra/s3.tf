resource "aws_s3_bucket" "bucket-app" {
  bucket = "cars-images"
  acl    = "private"

  tags = {
    Maintainer = "elias@worker.com"
  }
}