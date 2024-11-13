## RAG workflow
 - [ ] User asks a question
 - [ ] embed the question
 - [ ] Retrieve context using embedded question
 - [ ] Combine context and question
 - [ ] Retrieve answer using combined context and question

## Potential functions
- Chunkinator, turn text into chunks
- Taginator, add tags to chunks
- Contextinator, get context based on a question (2 steps, embed question then get context)

## Ideas while working
Add a clearance level to the chunk, so that the ai can filter out information that a user is not allowed to have.
tomorrow look at how the -search for vectors- method is asking for data. design json to that standard.  
logging can be done, let the user say when a response was not satisfactory. Than save that response and the question that lead to it + any other logging info