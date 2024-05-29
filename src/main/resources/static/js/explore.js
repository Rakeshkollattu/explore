    $(document).ready(function () {
$("#news-slider").owlCarousel({
items: 3,
itemsDesktop: [1199, 3],
itemsDesktopSmall: [980, 2],
itemsMobile: [600, 1],
navigation: true,
navigationText: ["", ""],
pagination: true,
autoPlay: true });
});

const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]')
const popoverList = [...popoverTriggerList].map(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl))

function search() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[0];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}

function clearInput() {
  document.getElementById('searchInput').value = '';
}


