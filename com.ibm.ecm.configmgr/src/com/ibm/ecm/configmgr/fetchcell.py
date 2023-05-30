# Usage: wsadmin -f addJvmProperty.py [node:]server property value [description]
# e.g. wsadmin -f addJvmProperty.py all property value
# e.g. wsadmin -f addJvmProperty.py myNode:myServer property value
# e.g. wsadmin -f addJvmProperty.py myServer property value

print AdminControl.getCell()
