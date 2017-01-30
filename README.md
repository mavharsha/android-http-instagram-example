# android-http-instagram-example

## Getting Started

### Setting up data model

To run this example, please create a [Graphcool](http://graph.cool) account and **copy your endpoint**.

![](http://i.imgur.com/ytXDR4B.gif)

This is the needed data model:

```graphql
type Post {
  description: String!
  imageUrl: String!
}
```

### Configure app endpoint

Open `InstagramHTTPExample/app/src/main/java/graph/cool/instagramhttpexample/MainActivity.java` and replace `__PROJECT_ID__` with your endpoint.

```java
Request request = new Request.Builder()
  .url("https://api.graph.cool/simple/v1/__PROJECT_ID__")
  .post(body)
  .build();
```

### Run application

Open the project with Android Studio, build and run. Enjoy!

## Help & Community [![Slack Status](https://slack.graph.cool/badge.svg)](https://slack.graph.cool)

Join our [Slack community](http://slack.graph.cool/) if you run into issues or have questions. We love talking to you!

![](http://i.imgur.com/5RHR6Ku.png)
