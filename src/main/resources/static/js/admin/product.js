$('#createProduct').click((e)=>{
  clearForm();

  $('#modalTitle').text('Tambah produk');
  $('#modalDesc').text('Silahkan berikan detail produk');
  $('#saveForm').attr('action', '/admin/product-management/product/save');
  $('#modalForm').modal('show');
});

function clearForm () { 
  $('#modalFieldId').val(null);
  $('#modalFieldName').val(null);
  $('#modalFieldCategoryId').val(null);
  $('#modalFieldSlug').val(null);
  $('#modalFieldDeleted').prop('checked', false);
 }

function deleteData(el){
  $('#deleteModalButton').attr('data-id', $(el).data('id'));
  $('#deleteName').text($(el).data('name'));
  $('#deleteSlug').text($(el).data('slug'));
  $('#deleteActive').prop('checked', $(el).data('deleted'));
  $('#deleteModal').modal('show');
 }

 document.getElementById('deleteModalButton')?.addEventListener('click', (e)=>{
  let id = $('#deleteModalButton').data('id');
  $.ajax({
    type: "get",
    url: `/admin/product-management/product/delete/${id}`,
    dataType: "html",
    success: function (response) {
      location.reload();
    }
  });
});