CAFFE_PATH=/home/luis/Caffe/caffe/
MEAN_PATH=/home/luis/Caffe/caffe/python/caffe/imagenet/ilsvrc_2012_mean.npy

python ./classify_image.py $1 $2 $3 $CAFFE_PATH $MEAN_PATH
