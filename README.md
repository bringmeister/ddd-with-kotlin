DDD with Kotlin
===============

This is a simple demo project to show a Domain Driven Design with Kotlin.

## Run it

```
./gradlew bootRun
```

Then:

```
POST to http://localhost:8080/master_data_update
POST to http://localhost:8080/media_data_update
```

## Example Use Case

The subject of this demo is the `Product Service`.
The `Product Service` is located between five other components.
The `Master Data Service` will push new products to the `Product Service`.
This is the beginning of our business process.
The `Product Service` will register each new product at the `Media Data Service`.
After a product is registered, the `Product Service` will receive updates for this product from the `Media Data Service`. 
After an update was received, the `Product Service` will:
 - Update the `CDN` if media data has changed
 - Update the `Shop` and `search index` if master data has changed

```
+-------------+
| Master Data |
|   Service   |
+-------------+
   |                     << DEMO >>   
   +------------------► +---------+
    1: update product   | Product |
                        | Service |---------+---------+
               +------► +---------+         |         |
      3:       |             |      2:      |         |        5:
 push updates  |             | register new |         | update if master 
               |             |   product    |         | data has changed
   +------------+            |              |         |
   | Media Data | ◄----------+              |         +----------+
   |  Service   |                           |         |          |
   +------------+        4: update if media |         |          |
                          data has changed  |         |          |
                                            ▼         ▼          ▼
                                         +-----+  +------+  +--------+ 
                                         | CDN |  | Shop |  | Search |
                                         +-----+  +------+  +--------+
```

## What to see

- A domain entity called `Product.kt` which encapsulates business logic and throws domain events.
- A process flow with events ("something has happened") and commands ("now do something").
- Value objects such as `ProductNumber.kt` or `ProductInformation.kt`.
- A ports-and-adapters package layout.