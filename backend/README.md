
# SunCycle Backend

## Steps to install (for MacOS)
1. Ensure java installed (BellSoft on Apple Silicon chip) and `brew install gradle`
2. `brew install mysql`
3. `brew install google-cloud-sdk`
4. Run `gcloud init`
5. Ensure logged in to gcloud: `gcloud auth application-default login`
6. From /backend, run `./cloud-sql-proxy confident-run-397114:australia-southeast1:suncycle`
7. Run `mysql -u root -p --host 127.0.0.1 --port 3306`
8. `gradle bootRun`
