function List({title, authors, thumbnail, link}) {

  // handleClick(link){

  // }
    return (
        <div className="book-item">
          <img src={thumbnail} alt={title} />
          <p><b>{title}</b></p>
          <span>by {authors}</span>
          <button className="btn-primary">View</button>
        </div>
    )
}

function BookHomeList({data}){
  return (
        <div className="book-list">
            {data?.slice(0, 4).map((book) => {
              return(
                <List
                  key = {book.id}
                  title = {book.volumeInfo.title}
                  authors = {book.volumeInfo.authors?.join(", ")}
                  thumbnail = {book.volumeInfo.imageLinks?.thumbnail}
                  link = {book.selfLink}
                />
              )
            })}
        </div>
  )
}


export default BookHomeList