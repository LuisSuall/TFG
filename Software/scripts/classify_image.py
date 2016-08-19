import numpy as np
import os
import sys

def main(argv):
    caffe_root = argv[5]
    sys.path.insert(0,caffe_root+'python')

    import caffe

    caffe.set_mode_cpu()

    model_def = argv[3]
    model_weights = argv[4]

    net = caffe.Net(model_def,
                    model_weights,
                    caffe.TEST)

    mean_path = ''
    mu = np.load(mean_path)
    mu = mu.mean(1).mean(1)

    transformer = caffe.io.Transformer({'data':net.blobs['data'].data.shape})
    transformer.set_transpose('data', (2,0,1))
    transformer.set_mean('data',mu)
    transformer.set_raw_scale('data',255)
    transformer.set_channel_swap('data',(2,1,0))

    net.blobs['data'].reshape(1,3,227,227)

    image = caffe.io.load_image(argv[2])
    transformed_image = transformer.preprocess('data',image)
    net.blobs['data'].data[...] = transformed_image

    output = net.fordward()
    output_prob = output['prob'][0]

    for prob in output_prob:
        print prob
        print '\n'

if __name__ = '__main__':
    main(sys.argv)
