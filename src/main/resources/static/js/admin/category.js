$('#createCategory').click((e)=>{
  clearForm();

  $('#modalTitle').text('Tambah Kategori');
  $('#modalDesc').text('Silahkan berikan detail kategori');
  $('#saveForm').attr('action', '/admin/product-management/category/create');
  $('#modalForm').modal('show');
});

function editForm(el) {
  clearForm();
  
  let id = $(el).data('id'),
  name = $(el).data('name'),
  description = $(el).data('description'),
  slug = $(el).data('slug'),
  deleted = $(el).data('deleted');

  $('#modalTitle').text('Edit Kategori '+ name);
  $('#modalDesc').text('Silahkan berikan detail kategori');
  $('#saveForm').attr('action', '/admin/product-management/category/update/'+id);
  $('#modalFieldId').val(id);
  $('#modalFieldName').val(name);
  $('#modalFielDescription').val(description);
  $('#modalFieldSlug').val(slug);
  $('#modalFieldDeleted').prop('checked', deleted);
  
  $('#modalForm').modal('show');
}

function clearForm() { 
  $('#modalFieldId').val(null);
  $('#modalFieldName').val(null);
  $('#modalFielDescription').val(null);
  $('#modalFieldSlug').val(null);
  $('#modalFieldDeleted').prop('checked', false);
 }