//load("D:/Eagle Guitars git/guitar-schop/src/scripts/delete/delete-document.js")

db.goods_collection.deleteOne({name : "Документ для удаления"})

print('To-delete document was deleted.')