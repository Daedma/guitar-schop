//load("D:/Eagle Guitars git/guitar-schop/src/scripts/create/create-categories-collection.js")

db.categories_collection.insertMany( [
    {
        name: 'Guitar',
        goods_id: null // гитары
    },

    {
        name: 'Strings',
        goods_id: null // струны
    },
] )
print('Categories collection was created.')