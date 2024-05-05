//load("D:/Eagle Guitars git/guitar-schop/src/scripts/create/create-reviews-collection.js")

db.reviews_collection.insertMany( [
    {
        author_id: null, // ivanshapoval
        goods_id: null, // струны
        publishing_date: new Date('2024-04-09T18:01:01Z'),
        content: 'Хорошие струны. Очень нравятся',
        rate: 5.0
    },
] )

print('Reviews collection was created.')