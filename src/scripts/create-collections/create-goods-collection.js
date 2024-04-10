//load("D:/Eagle Guitars git/guitar-schop/src/scripts/create/create-goods-collection.js")

db.goods_collection.insertMany( [
    {
        name: 'Fender Ritchie Blackmore Stratocaster',
        description: 'Именная модель гитариста Deep Purple Ричи Блэкмора',
        cost: 216000.00,
        // images: [],
        remaining: 7,
        rate: 4.2,
        categories_id: null,
        type: 'Guitar',
        form: 'Stratocaster',
        frets: 21
    },

    {
        name: 'Dunlop DEN1046',
        description: 'Комплект струн фирмы Dunlop, калибр 10-46, никелированные',
        cost: 1010.00,
        // images: [],
        remaining: 19,
        rate: 4.8,
        categories_id: null,
        type: 'Strings',
        gauge: '10-46',
        material: 'Никель'
    },

    {
        name: 'Gibson Les Paul Modern Burgundy',
        description: 'Настоящее американское звучание по доступной цене!',
        cost: 310825.7,
        remaining: 3,
        rate: 5.0,
        categories_id: null,
        type: 'Guitar',
        form: 'Les Paul',
        frets: 22
    }
] )

print('Goods collection was created.')