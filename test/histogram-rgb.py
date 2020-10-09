#  projeto em python: 
#- carregar uma imagem: jpg, png ou titf 
#- visualizar o histograma de cor de cada canal (r, g, b)
# dependencias : OpenCV e Matplotlib

import numpy as np
import cv2 as cv
import argparse
from matplotlib import pyplot as plt

# img = cv.imread("Lenna.jpg") # aceita formatos extras .jpg, png e .tiff

# 构造参数解析器
ap = argparse.ArgumentParser()
ap.add_argument("-i", "--image", required=True, help="Path to the image")
args = vars(ap.parse_args())

# 加载图像
img = cv.imread(args["image"])

(b,g,r) = cv.split(img)
cv.imshow("Resultado", img)

red = plt.subplot(331)
red = plt.hist(r.ravel(), 256, [0, 256], color="red")

green = plt.subplot(332)
green = plt.hist(g.ravel(), 256, [0, 256], color="green")

blue = plt.subplot(333)
blue = plt.hist(b.ravel(), 256, [0, 256], color="blue")

rgb = plt.subplot(212)
rgb = plt.hist(r.ravel(), 256, [0, 256], color="red") + plt.hist(g.ravel(), 256, [0, 256], color="green") + plt.hist(b.ravel(), 256, [0, 256], color="blue" ) 


plt.show()

cv.waitKey(0)
cv.destroyAllWindows()