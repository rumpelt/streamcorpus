import os
import uuid
from . import make_stream_item, ContentItem, Chunk, serialize, deserialize

TEST_XZ_PATH = os.path.join(os.path.dirname(__file__), '../../../test-data/john-smith-tagged-by-lingpipe-0.sc.xz')

def make_si():
    si = make_stream_item( None, 'http://example.com' )
    si.body = ContentItem(raw='hello!')
    return si

def test_chunk():
    ## write in-memory
    ch = Chunk()
    assert ch.mode == 'wb'
    si = make_si()
    ch.add( si )
    assert len(ch) == 1

def test_xz():
    count = 0
    for si in Chunk(TEST_XZ_PATH):
        count += 1
        assert si.body.clean_visible
    assert count == 197

path = '/tmp/test_chunk-%s.sc' % str(uuid.uuid1())

def test_chunk_path_write():
    ## write to path
    ch = Chunk(path=path, mode='wb')
    si = make_si()
    ch.add( si )
    assert len(ch) == 1
    print repr(ch)

def test_chunk_path_append():
    ## append to path
    ch = Chunk(path=path, mode='ab')
    si = make_si()
    ch.add( si )
    ch.close()
    ## count is only for those added
    assert len(ch) == 1
    print repr(ch)

def test_chunk_path_read():
    ## read from path
    ch = Chunk(path=path, mode='rb')
    assert len(list(ch)) == 2
    ## updated by __iter__
    assert len(ch) == 2
    print repr(ch)

def test_chunk_data_read():
    ## load from data
    data = open(path).read()
    ch = Chunk(data=data, mode='rb')
    assert len(list(ch)) == 2
    ## updated by __iter__
    assert len(ch) == 2
    print repr(ch)

def test_chunk_data_append():
    ## load from data
    data = open(path).read()
    ch = Chunk(data=data, mode='ab')
    si = make_si()
    ch.add( si )
    assert len(ch) == 1
    print repr(ch)

def test_serialize():
    si = make_si()
    blob = serialize(si)
    si2 = deserialize(blob)
    assert si.stream_id == si2.stream_id

    
