CAFFE_PATH=/home/luis/Caffe/caffe/
MEAN_PATH=/home/luis/Caffe/caffe/python/caffe/imagenet/ilsvrc_2012_mean.npy

export LD_LIBRARY_PATH=/usr/local/cuda/lib64:$LD_LIBRARY_PATH
export PATH=/usr/local/cuda/bin:$PATH

python ../scripts/classify_image.py $1 $2 $3 $CAFFE_PATH $MEAN_PATH
