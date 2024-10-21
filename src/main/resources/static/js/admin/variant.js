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
    url: `/admin/product-management/variant/delete/${id}`,
    dataType: "html",
    success: function (response) {
      location.reload();
    }
  });
});