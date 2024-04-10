//load("D:/Eagle Guitars git/guitar-schop/src/scripts/delete/insert-document.js")

db.goods_collection.insertOne(
    {
        name: 'Документ для удаления',
        description: 'Документ для удаления',
        cost: 0.01,
        remaining: 1,
        rate: 5.0,
    })

print('To-delete document was created.')