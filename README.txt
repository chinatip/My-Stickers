Members:
1. Chinatip 5710546551
2. Visut 5710546615
3. Naphat 5710547191
4. Parinvut 5710546305

Design Patterns

1. Adapter
- GridViewAdapter uses for wrap the ImageItem class (which used to store title and path of the Image) to show in GalleryActivity
- StickerAdapter uses for wrap the StickerRow (which contains 4 path of stickers in a row that show in ChooseActivity)

2. Singleton
- To save file to external storage, Save.class is only a class that can do it.

3. Decorator
- Each item in ChooseActivity(which is stickerGallery)and GalleryActivity open different images/stickers dynamically.

4. Chain of Responsibility
- In MainActivity, we can either go to Gallery (to choose image to edit or view) or Camera which can take a photo. After that, both action bring to the same page which is Edit photo(EditPhotoActivity) and then add sticker page(AddStickerActivity). All along the way only one more image will be saved (for 1 click). 

5. Memento
- In AddStickerActvity, Memento pattern is in reverse button when you click and then you can go back to the last save.



GRASP
Creator
-
Information Expert
Low Coupling
- Save is only a class that can save images to storage.
Controller
High Cohesion
Indirection
Polymorphism
- Each time
Protected Variations
Pure Fabrication