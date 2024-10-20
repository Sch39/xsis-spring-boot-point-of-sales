$('#createCategory').click((e)=>{
  $('#modalTitle').text('Tambah Kategori');
  $('#modalDesc').text('Silahkan berikan detail kategori');
  // $('#saveForm').attr('action', '/admin/product-management/category/create');
  $('#modalForm').modal('show');
});
