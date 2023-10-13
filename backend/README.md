
# SunCycle Backend

## Steps to install (for MacOS)
1. Ensure java installed (BellSoft on Apple Silicon chip) and `brew install gradle`
2. `brew install mysql`
3. `brew install google-cloud-sdk`
4. Run `gcloud init`
5. Ensure logged in to gcloud: `gcloud auth application-default login`
6. From /backend, run `./cloud-sql-proxy confident-run-397114:australia-southeast1:suncycle`
7. Run `mysql -u root -p --host 127.0.0.1 --port 3306`, no password
8. In backend dir, `gradle build` then `gradle bootRun`

## Endpoints and Usage

[Link to Postman workspace](https://app.getpostman.com/join-team?invite_code=67841360f9e8dcd092f6c344c23c586f&target_code=8c520392bec63caead7a8e3e0a76708a)

### Users (http://localhost:8000/users)



### Installations (http://localhost:8000/installations)



### Panels (http://localhost:8000/panels)



### Marketplace (http://localhost:8000/marketplace)


