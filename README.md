# EndpointStatusChecker
Simple api to test endpoints statuses writen in Kotlin with swagger to test ui.

- /endpoint (GET, POST)
get or add endpoint
- /endpoint/checkAll
check all endpoind defined in db
- /endpoint/{endpointId}
check endpoint by id
- /addresseeMail (GET, POST)
get or add mail addressee

Checking endpoint is beeing Scheduled, if endpoint status != 200 then send mail to all addressee in db.



