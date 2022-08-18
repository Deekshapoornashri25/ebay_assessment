# Item validation and creation



## Getting started

API Details - 
    Method - POST
    URL    - {{baseUrl}}/item
    Body   - {
                "id": "!",
                "siteId": 1,
                "categoryId": 1,
                "title": "title",
                "condition": "condition",
                "price": 1,
                "quantity": 1,
                "imageUrl": [
                    "url"
                    ],
                "itemSpecifies": [
                    [{
                        "model": "aa"
                    },
                    {
                     "model": "aaa"
                    }]
                ],
                "description": "11"
            }

## Validation applied 
1. Title length is under 85 characters
2. Number of Item Specifics between 2 - 10
3. Item Specifics must include a ‘Model` with a non empty value 
4. Site ID can only between 0 - 100
5. Item Specifics - Capitalize first letter and lower case all the rest of the letters
6. Before calling Item specific normalized - Add `Thread.sleep` to each call to mimic a slow response time of 1000ms

## Response
{
"id": "cl6yj3u4t0002p218mxgaaywp",
"siteId": 10,
"categoryId": 1,
"title": "title",
"condition": "condition",
"price": 1.0,
"quantity": 1,
"imageUrl": [
"1"
],
"itemSpecifies": [
{
"color": null,
"model": "Aa"
},
{
"color": null,
"model": "Aaa"
}
],
"description": "11"
}