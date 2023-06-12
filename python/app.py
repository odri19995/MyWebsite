import streamlit as st

# st.title("this is title")
# st.write("this is text")

# """
# # This is title
# ## this is subtitle


# - first
# - second
# - third


# """

text = st.text_input("input here")

st.write(text)