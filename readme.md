# My Stickers
My Stickers is a group project for Software Specification course. This application is native android application. This application expected to be able to add stickers to pictures and has its gallery. 

However, there is some problem with sending file size, we got more poor quality of the picture in each sending.  

### Using Design Patterns
This application applied some design patterns which are Adapter, Singleton, Decorator, Chain of Responsability and Memento patterns.

- Adapter
    - GridViewAdapter uses for wrap the ImageItem class (which used to store title and path of the Image) to show in GalleryActivity
    - StickerAdapter uses for wrap the StickerRow (which contains 4 path of stickers in a row that show in ChooseActivity)
- Singleton
    - To save file to external storage, Save.class is only a class that can do it.

- Decorator
    - Each item in ChooseActivity(which is stickerGallery)and GalleryActivity open different images/stickers dynamically.

- Chain of Responsibility
    - In MainActivity, we can either go to Gallery (to choose image to edit or view) or Camera which can take a photo. After that, both action bring to the same page which is Edit photo(EditPhotoActivity) and then add sticker page(AddStickerActivity). All along the way only one more image will be saved (for 1 click). 

- Memento
    - In AddStickerActvity, Memento pattern is in reverse button when you click and then you can go back to the last save.
    
### Using GRASP
This application also applied some GRASP as well which are Creator, Low Coupling, Indirection and Protected Variations.
- Creator
    - In Gallery of sticker and photo(GalleryActivity and ChooseActivity) creates photo in gallery.

- Low Coupling
    - Save is only a class that can save images to storage.

- Indirection
    - Using adapters(GridViewAdapter, StickerAdapter) to avoid a direct coupling between two or more elements

- Protected Variations
    - Each time creating a gallery, it will call ImageItem or StickerRow for many times. To avoid impact of variations of some
elements on the other elements, each class of ImageItem and StrickRow contains different path of photo and show it in gallery.

### Issues
- GalleryActivity 
    - This class show other images that is not from this app because we cannot load file into the app. So, we use other images that stored in Drawable instead to show that GalleryActicity can show images.

- The Quality of the images from this app
    - After we take a photo and edit it in the app, we find some problems that quality is drop from sending it to our app. It might be the way that we transfer the data is not good enough.

- Sticker
    - Sticker is drawn together with the image in AddStickerActivity.
    - We think that the quality of image is based on the image that we add.

### References
- [StickerView For Android](https://github.com/sangmingming/StickerView)
    - sticker view for android, can translate, scale, rotate.
- [Gallery](https://mycodeandlife.wordpress.com/2012/09/06/a-cool-photo-gallery/) 
